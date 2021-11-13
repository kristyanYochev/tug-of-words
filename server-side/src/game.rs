use std::thread;
use std::thread::JoinHandle;
use std::sync::mpsc::{Receiver, Sender};
use std::sync::mpsc;
use std::net::TcpStream;
use std::io::{Write, BufReader, BufRead};

pub struct Game {

}

pub enum GameMessage {
    PlayerJoin(TcpStream)
}

impl Game {
    pub fn new() -> Self {
        Self {}
    }

    pub fn run(mut self) -> (JoinHandle<()>, Sender<GameMessage>) {
        let (tx, rx) = mpsc::channel();

        let thread = thread::spawn(move || {
            self.execution_loop(rx);
        });

        (thread, tx)
    }

    fn execution_loop(&mut self, rx: Receiver<GameMessage>) {
        loop {
            let message = rx.recv().unwrap();

            match message {
                GameMessage::PlayerJoin(stream) => self.connect_player(stream),
            }
        }
    }

    fn connect_player(&mut self, mut stream: TcpStream) {
        let name = {
            let mut reader = BufReader::new(&stream);
            let mut buffer = String::new();
            reader.read_line(&mut buffer).unwrap();
            buffer.trim().to_string()
        };

        println!("Player {} connected", name);
        stream.write(format!("Welcome, {}\n", name).as_bytes()).unwrap();
        stream.flush().unwrap();
    }
}