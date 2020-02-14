﻿namespace Tvl.Java.DebugInterface.Events.Contracts
{
    using System;
    using System.Diagnostics.Contracts;

    [ContractClassFor(typeof(IMethodEntryEvent))]
    internal abstract class IMethodEntryEventContracts : IMethodEntryEvent
    {
        #region IMethodEntryEvent Members

        public IMethod GetMethod()
        {
            Contract.Ensures(Contract.Result<IMethod>() != null);
            Contract.Ensures(this.GetVirtualMachine().Equals(Contract.Result<IMethod>().GetVirtualMachine()));

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
