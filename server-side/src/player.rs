use std::net::TcpStream;
use std::io::{Read, Write};

pub struct Player {
    connection: TcpStream,
}

impl Player {
    pub fn from_connection(connection: TcpStream) -> Self {
        Self {
            connection
        }
    }

    pub fn handle(&mut self) {
        'conn: loop {
            let mut buffer = [0; 1024];

            self.connection.read(&mut buffer).unwrap();

            let response = &buffer;

            self.connection.write(response).unwrap();
            self.connection.flush().unwrap();

            if String::from_utf8_lossy(&buffer).starts_with("quit") {
                break 'conn;
            }
        }
    }
}