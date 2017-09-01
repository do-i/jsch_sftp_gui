package com.djd.fun.jsch;

import java.util.concurrent.atomic.AtomicLong;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import com.jcraft.jsch.SftpProgressMonitor;

public class ProgressMonitor implements SftpProgressMonitor {

  private final BoundedRangeModel boundedRangeModel;
  private final AtomicLong totalCount;

  public ProgressMonitor() {
    this.boundedRangeModel = new DefaultBoundedRangeModel();
    this.totalCount = new AtomicLong();
  }

  public BoundedRangeModel getBoundedRangeModel() {
    return boundedRangeModel;
  }

  @Override
  public void init(int op, String src, String dest, long max) {
    totalCount.set(0);
    boundedRangeModel.setMaximum((int)max);
    boundedRangeModel.setMinimum(0);
    boundedRangeModel.setValue(totalCount.intValue());
  }

  @Override
  public boolean count(long count) {
    totalCount.addAndGet(count);
    boundedRangeModel.setValue(totalCount.intValue());
    return true;
  }

  @Override
  public void end() {
    boundedRangeModel.setValue(boundedRangeModel.getMaximum());
  }
}
