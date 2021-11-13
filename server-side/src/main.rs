pub mod player;
pub mod game;

pub mod prelude {
    pub use crate::player::*;
    pub use crate::game::*;
}

use std::net::TcpListener;
use crate::prelude::*;

fn main() {
    let game = Game::new();
    let (game_thread, game_tx) = game.run();

    let listener = TcpListener::bind("127.0.0.1:6969").unwrap();

    for stream in listener.incoming() {
        let stream = stream.unwrap();

        game_tx.send(GameMessage::PlayerJoin(stream)).unwrap();
    }
    game_thread.join().unwrap();
}
