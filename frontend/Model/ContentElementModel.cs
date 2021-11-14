using frontend.Core;
using frontend.View;
using System;
using System.Collections.Generic;
using WPFChatApp.Core;

namespace frontend.Model
{
    public abstract class ContentElementModel : ObeservableObject
    {
        private int _xPos;

        public int XPos
        {
            get => _xPos;
            set
            {
                _xPos = value;
                OnPropertyChanged();
            }
        }

        private int _yPos;

        public int YPos
        {
            get => _yPos;
            set
            {
                _yPos = value;
                OnPropertyChanged();
            }
        }

        private readonly ContentElement elem;

        public ContentElementModel()
        {
            elem = new ContentElement
            {
                DataContext = this,
                Content = MakeContent()
            };
        }

        public ContentElement GetElement()
        {
            return elem;
        }

        public object GetContent()
        {
            return elem.Content;
        }

        public void Update(Dictionary<string, string> param)
        {
            string value;
            if (param.TryGetValue("xPos", out value))
            {
                XPos = int.Parse(value);
            }

            if (param.TryGetValue("yPos", out value))
            {
                YPos = int.Parse(value);
            }

            ClientUpdate(param);
        }

        public virtual void ClientUpdate(Dictionary<string, string> param)
        {

        }

        public abstract object MakeContent();
    }
}
