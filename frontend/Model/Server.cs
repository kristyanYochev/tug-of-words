using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace frontend.Model
{
    class Server
    {
        private readonly Task server;
        //private readonly Task sender;
        private readonly Action<string> onFramePart;
        private readonly Queue<Tuple<int, string, Dictionary<string, string>>> eventBuffer;

        public Server(Action<string> onFramePart)
        {
            eventBuffer = new Queue<Tuple<int, string, Dictionary<string, string>>>();
            this.onFramePart = onFramePart;

            server = new Task(() =>
            {
                TcpListener listener = new TcpListener(IPAddress.Loopback, 6969);
                listener.Start();
                Debug.WriteLine("AWAITING CLIENT");

                TcpClient client = listener.AcceptTcpClient();
                NetworkStream stream = client.GetStream();
                Debug.WriteLine("client connected");

                string frame;
                while ((frame = ReadRawFrame(stream)) != "")
                {
                    foreach (string part in frame.Split('\n'))
                    {
                        Application.Current.Dispatcher.Invoke(() => onFramePart(part));
                    }

                    SendEvents(stream, eventBuffer);
                }

                Debug.WriteLine("client dissconected");
                listener.Stop();
            });

            #region
            //sender = new Task(() =>
            //{
            //    Debug.WriteLine("connecting to server");
            //    TcpClient socket = new TcpClient();
            //    socket.Connect(IPAddress.Loopback, 6969);
            //    NetworkStream stream = socket.GetStream();
            //    Debug.WriteLine("connected to server");

            //    byte[] data;
            //    data = Encoding.ASCII.GetBytes("this is data owo\n");
            //    stream.Write(data, 0, data.Length);
            //    data = Encoding.ASCII.GetBytes("so is this\n");
            //    stream.Write(data, 0, data.Length);
            //    data = Encoding.ASCII.GetBytes("end");
            //    stream.Write(data, 0, data.Length);
            //    byte[] _;
            //    _ = new byte[1];
            //    stream.Read(_, 0, 1);

            //    data = Encoding.ASCII.GetBytes("this is data owo\n");
            //    stream.Write(data, 0, data.Length);
            //    data = Encoding.ASCII.GetBytes("so is this\n");
            //    stream.Write(data, 0, data.Length);
            //    data = Encoding.ASCII.GetBytes("end");
            //    stream.Write(data, 0, data.Length);
            //    _ = new byte[1];
            //    stream.Read(_, 0, 1);

            //    stream.Close();
            //    socket.Close();
            //    Debug.WriteLine("Client closed");
            //});
            #endregion

            server.Start();
            //sender.Start();
        }

        public void BufferEvent(Tuple<int, string, Dictionary<string, string>> e)
        {
            eventBuffer.Enqueue(e);
        }

        private void SendEvents(NetworkStream stream, Queue<Tuple<int, string, Dictionary<string, string>>> queue)
        {
            string strData = "";
            while (eventBuffer.Count > 0)
            {
                string parsedE = PasrseEvent(eventBuffer.Dequeue());
                strData += parsedE;
            }
            strData += "-";
            byte[] data = Encoding.ASCII.GetBytes(strData);
            stream.Write(data, 0, data.Length);

            byte[] _ = new byte[1];
            stream.Read(_, 0, 1);
        }

        private static string PasrseEvent(Tuple<int, string, Dictionary<string, string>> e)
        {
            return $"{e.Item1}:{e.Item2}:{string.Join(",", e.Item3.Keys.Select(x => $"{x}={e.Item3[x]}"))}\n";
        }

        private string ReadRawFrame(NetworkStream stream)
        {
            string stitchedData = "";
            string received;

            do
            {
                received = ReadStream(stream);
                if (received.Length == 0)
                {
                    return "";
                }

                stitchedData += received;
            } while (!received.EndsWith("-"));

            stream.Write(new byte[] { 0 }, 0, 1);
            return stitchedData[0..^1];
        }

        private string ReadStream(NetworkStream stream)
        {
            byte[] receivedBytes = new byte[256];
            int readLength = stream.Read(receivedBytes, 0, receivedBytes.Length);
            string received = Encoding.ASCII.GetString(receivedBytes, 0, readLength);
            return received;
        }
    }
}
