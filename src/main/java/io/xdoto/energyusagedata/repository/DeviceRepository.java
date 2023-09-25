package io.xdoto.energyusagedata.repository;

import io.xdoto.energyusagedata.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
