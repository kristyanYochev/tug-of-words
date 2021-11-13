pub mod player;

use std::net::{TcpListener, TcpStream};

pub mod prelude {
    pub use crate::player;
}

fn main() {
    let listener = TcpListener::bind("127.0.0.1:6969").unwrap();

    for stream in listener.incoming() {
        let stream = stream.unwrap();

        handle_connection(stream);
    }
}

fn handle_connection(stream: TcpStream) {
    use crate::player::Player;
    let mut player = Player::from_connection(stream);
    player.handle();
}
