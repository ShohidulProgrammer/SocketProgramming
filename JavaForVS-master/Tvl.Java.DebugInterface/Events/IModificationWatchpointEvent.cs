﻿namespace Tvl.Java.DebugInterface.Events
{
    using System.Diagnostics.Contracts;

    [ContractClass(typeof(Contracts.IModificationWatchpointEventContracts))]
    public interface IModificationWatchpointEvent : IWatchpointEvent
    {
        IValue GetNewValue();
    }
}
