package org.tnova.service.selection.service;

import org.tnova.service.selection.domain.vnf.VnfDescriptor;

public interface VnfService
{
    VnfDescriptor getVnfById( String vnfdId );
}
