package com.upchardwar.app.services.lab;

import com.upchardwar.app.entity.payload.LabRequest;
import com.upchardwar.app.entity.payload.LabResponse;

public interface ILabService {
  public LabResponse registerLab(LabRequest labRequest);
}
