﻿namespace Tvl.VisualStudio.Language.Java.SourceData
{
    using System.Collections.Generic;
    using System.Diagnostics.Contracts;

    public class CodePackageStatement : CodeElement
    {
        public CodePackageStatement(string name, string fullName, CodeLocation location, CodeElement parent)
            : base(name, fullName, location, parent)
        {
            Contract.Requires(!string.IsNullOrEmpty(name));
            Contract.Requires(!string.IsNullOrEmpty(fullName));
            Contract.Requires(location != null);
            Contract.Requires(parent != null);
        }

        public override void AugmentQuickInfoSession(IList<object> content)
        {
        }
    }
}
