package com.cqlybest.common.web;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A custom {@link javax.servlet.ServletOutputStream} for use by our filters
 */
public class FilterServletOutputStream extends ServletOutputStream {

  private OutputStream stream;

  /**
   * Creates a FilterServletOutputStream.
   */
  public FilterServletOutputStream(final OutputStream stream) {
    this.stream = stream;
  }

  /**
   * Writes to the stream.
   */
  public void write(final int b) throws IOException {
    stream.write(b);
  }

  /**
   * Writes to the stream.
   */
  public void write(final byte[] b) throws IOException {
    stream.write(b);
  }

  /**
   * Writes to the stream.
   */
  public void write(final byte[] b, final int off, final int len) throws IOException {
    stream.write(b, off, len);
  }
}
