package com.github.douyaba.web.protocol.model;

import org.apache.commons.io.output.TeeOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author s.c.gao
 */
@Deprecated
public class HttpServletResponseWrapper extends javax.servlet.http.HttpServletResponseWrapper {

    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    private PrintWriter writer;

    public HttpServletResponseWrapper(HttpServletResponse response) {
        super(response);
        this.writer = new PrintWriter(this.bos);
    }

    @Override
    public ServletResponse getResponse() {
        return this;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStream() {
            private TeeOutputStream tee =
                    new TeeOutputStream(
                            HttpServletResponseWrapper.super.getOutputStream(),
                            HttpServletResponseWrapper.this.bos);

            @Override
            public void write(int b) throws IOException {
                this.tee.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener writeListener) {
            }
        };
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new TeePrintWriter(super.getWriter(), this.writer);
    }

    public byte[] toByteArray() {
        return this.bos.toByteArray();
    }

    /**
     * Get response body
     *
     * @return String of request body
     */
    public String getResponseBody() {
        return new String(toByteArray(), StandardCharsets.UTF_8);
    }

    private class TeePrintWriter extends PrintWriter {
        PrintWriter branch;

        public TeePrintWriter(PrintWriter main, PrintWriter branch) {
            super(main, true);
            this.branch = branch;
        }

        @Override
        public void write(char[] buf, int off, int len) {
            super.write(buf, off, len);
            super.flush();
            this.branch.write(buf, off, len);
            this.branch.flush();
        }

        @Override
        public void write(String s, int off, int len) {
            super.write(s, off, len);
            super.flush();
            this.branch.write(s, off, len);
            this.branch.flush();
        }

        @Override
        public void write(int c) {
            super.write(c);
            super.flush();
            this.branch.write(c);
            this.branch.flush();
        }

        @Override
        public void flush() {
            super.flush();
            this.branch.flush();
        }
    }
}
