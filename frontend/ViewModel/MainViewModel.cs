using frontend.Model;
using frontend.UIElements;
using frontend.View;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Text;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace frontend.ViewModel
{
    class MainViewModel
    {
        private Server server;
        public ObservableCollection<ContentElement> RenderedElems { get; }
        public object Buton { get; private set; }

        private Dictionary<int, ContentElementModel> models;

        public MainViewModel()
        {
            server = new Server(OnRenderAction);
            RenderedElems = new ObservableCollection<ContentElement>();
            models = new Dictionary<int, ContentElementModel>();
        }
        
        private void OnRenderAction(string action)
        {
            string[] split = action.Split(':');
            int id = int.Parse(split[0]);
            Dictionary<string, string> param = MakeDict(split[1]);

            if (id == 0)
            {
                id = int.Parse(param["id"]);
                MakeElement(id, param);
            }

            models[id].Update(param);
        }

        private Dictionary<string, string> MakeDict(string data)
        {
            Dictionary<string, string> ret = new Dictionary<string, string>();
            foreach(string str in data.Split(','))
            {
                string[] kv = str.Split('=');
                ret.Add(kv[0], kv[1]);
            }
            return ret;
        }

        private void MakeElement(int id, Dictionary<string, string> param)
        {
            ContentElementModel model = MakeObject(param);
            models.Add(id, model);
            RenderedElems.Add(model.GetElement());
        }

        private ContentElementModel MakeObject(Dictionary<string, string> param)
        {
            switch (param["type"])
            {
                case "Button_owo":
                    return new Test_Object();
                default:
                    break;
            }
            return null;
        }
    }
}
