using frontend.Model;
using System;
using System.Collections.Generic;
using System.Text;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace frontend.UIElements
{

    class Test_Object : ContentElementModel
    {
        public Test_Object() : base()
        {

        }

        public override object MakeContent()
        {
            return new Rectangle()
            {
                Fill = Brushes.Red
            };
        }

        public override void ClientUpdate(Dictionary<string, string> param)
        {
            string value;
            Rectangle content = (Rectangle)GetContent();
            if (param.TryGetValue("width", out value))
            {
                content.Width = int.Parse(value);
            }

            if (param.TryGetValue("height", out value))
            {
                content.Height = int.Parse(value);
            }
        }
    }
}
