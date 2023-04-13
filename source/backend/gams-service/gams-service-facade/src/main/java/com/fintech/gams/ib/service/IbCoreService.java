package com.fintech.gams.ib.service;

import com.ib.client.EClientSocket;

public interface IbCoreService
{
    EClientSocket getClient();
}
