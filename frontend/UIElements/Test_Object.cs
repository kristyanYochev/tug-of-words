using frontend.Model;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Text;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace frontend.UIElements
{

    class Test_Object : ContentElementModel
    {
        private readonly Action<Tuple<int, string, Dictionary<string, string>>> enqueue;
        public Test_Object(Action<Tuple<int, string, Dictionary<string, string>>> enqueue, int id) : base(id)
        {
            this.enqueue = enqueue;
        }

        public override object MakeContent()
        {
            Rectangle rect = new Rectangle()
            {
                Fill = Brushes.Red,
            };
            rect.MouseDown += Rect_MouseDown;

            return rect;
        }

        private void Rect_MouseDown(object sender, System.Windows.Input.MouseButtonEventArgs arg)
        {
            Debug.WriteLine(GetId());
            Tuple<int, string, Dictionary<string, string>> e = new Tuple<int, string, Dictionary<string, string>>(GetId(), "click", new Dictionary<string, string>());
            enqueue.Invoke(e);
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
