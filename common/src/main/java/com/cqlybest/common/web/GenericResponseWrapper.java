package com.cqlybest.common.web;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;


/**
 * Provides a wrapper for {@link javax.servlet.http.HttpServletResponseWrapper}.
 * <p/>
 * It is used to wrap the real Response so that we can modify it after that the target of the
 * request has delivered its response.
 * <p/>
 */
public class GenericResponseWrapper extends HttpServletResponseWrapper {

  private ServletOutputStream outstr;
  private PrintWriter writer;

  /**
   * Creates a GenericResponseWrapper
   */
  public GenericResponseWrapper(final HttpServletResponse response, final OutputStream outstr) {
    super(response);
    this.outstr = new FilterServletOutputStream(outstr);
  }

  @Override
  public ServletOutputStream getOutputStream() {
    return outstr;
  }

  @Override
  public PrintWriter getWriter() throws IOException {
    if (writer == null) {
      writer = new PrintWriter(new OutputStreamWriter(outstr, getCharacterEncoding()), true);
    }
    return writer;
  }

  @Override
  public void flushBuffer() throws IOException {
    if (writer != null) {
      writer.flush();
    }
    outstr.flush();
  }

}
