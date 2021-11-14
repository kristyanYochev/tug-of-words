using System;
using System.Collections.Generic;
using System.Text;

namespace frontend.Core
{
    public interface IUpdatable
    {
        public void update(Dictionary<string, string> param);
    }
}
