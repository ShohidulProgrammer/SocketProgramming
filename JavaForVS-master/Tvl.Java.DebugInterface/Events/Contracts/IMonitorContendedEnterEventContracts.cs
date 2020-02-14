﻿namespace Tvl.Java.DebugInterface.Events.Contracts
{
    using System;
    using System.Diagnostics.Contracts;

    [ContractClassFor(typeof(IMonitorContendedEnterEvent))]
    internal abstract class IMonitorContendedEnterEventContracts : IMonitorContendedEnterEvent
    {
        #region IMonitorEvent Members

        public IObjectReference GetMonitor()
        {
            throw new NotImplementedException();
        }

        #endregion

        #region IThreadEvent Members

        public IThreadReference GetThread()
        {
            throw new NotImplementedException();
        }

        #endregion

        #region IEvent Members

        public Request.IEventRequest GetRequest()
        {
            throw new NotImplementedException();
        }

        #endregion

        #region IMirror Members

        public IVirtualMachine GetVirtualMachine()
        {
            throw new NotImplementedException();
        }

        #endregion

        #region ILocatable Members

        public ILocation GetLocation()
        {
            throw new NotImplementedException();
        }

        #endregion
    }
}
