#!/usr/bin/env python3

import socket

HOST = '127.0.0.1'
PORT = 7080
address = ('127.0.0.1', 7080)


with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
    s.bind(address)
    s.listen()
    connection, client_addr = s.accept()
    
    buffer = connection.recv(2)
    print("Got data", buffer)

    while(True):
        data = connection.recv(1024)
        data = str(data)
        print(data)
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    """
    with conn:
        print('Connected by ', addr)
        while True:
            data = conn.recv(1024)
            if not data:
                break
            conn.sendall(data)
    """
