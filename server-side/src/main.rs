use std::net::{TcpListener, TcpStream};
use std::io::{Read, Write};

fn main() {
    let listener = TcpListener::bind("127.0.0.1:6969").unwrap();

    for stream in listener.incoming() {
        let stream = stream.unwrap();

        handle_connection(stream);
    }
}

fn handle_connection(mut stream: TcpStream) {
    'conn: loop {
        let mut buffer = [0; 1024];

        stream.read(&mut buffer).unwrap();

        let response = &buffer;

        stream.write(response).unwrap();
        stream.flush().unwrap();

        if String::from_utf8_lossy(&buffer).starts_with("quit") {
            break 'conn;
        }
    }
}
