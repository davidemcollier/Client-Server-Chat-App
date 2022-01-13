PROJECT TITLE: 
Networking Programming Project: Chat Application


SUMMARY:

This is an application allowing a server to send and receive messages with a client.
The application is written in Java and uses the Java Socket API.


HOW TO RUN:

Compile both ServerChat and ClientChat. Run the ServerChat first. A username is asked for and then awaits a client connection.
Next, run the ClientChat. A username also needs to be input. Once done, both may communicate back and forth in a concurrent manner. Either may enter '\q' to leave the chat.

EXPLANATION OF THE APPLICATION:

The application is composed of two classes: ServerChat and ClientChat. Both establish a socket connection through which input/output streams of data may be passed and converted into strings of legible user-friendly information (aided by buffers, to efficiently pass this data). 

The ServerChat class, sits awaiting a client to connect using a ServerSocket instance. This is contained in an infinite loop to ensure that even if a client disconnects, it will return to awaiting another client connection. 

The available port chosen, 5000 (1), is hardcoded into the application so the client can find and connect to the server. As the server is supposed to be in a constant state of awaiting the client to login, if for whatever reason it is not connected, the client is notified of this (either for a lapse in connection or failure to connect).

Both sides, when either ServerChat or ClientChat are launched, are invited to enter a username before entering the chatroom. Once entered, this will be appended to whatever message the other receives. 

With connection being accepted, both server and client launch two threads each. The first, listens for any messages received from the other and prints this to the console. The second allows each to send messages across the connection. The connection established is duplex, allowing for both the sending and receiving of messages at the same time. The use of threads is necessary as the PrintWriter (receiving of data) and the BufferedReader (sending of data) would be mutually blocking. 

Both users are able to exit the chat by entering "\q" and hitting Enter. In the server's case, it will continue to remain online to receive new chats from new connections. The client though, when the server disconnects in this instance is notified of the disconnection. 




REFERENCES:

1. List of ports - https://en.wikipedia.org/wiki/List_of_TCP_and_UDP_port_numbers

2. Java socket programming - Simple client server program: https://www.youtube.com/watch?v=-xKgxqG411c


Other Sources used:
- Use of character '\' - https://www.informit.com/articles/article.aspx?p=30241&seqNum=3 

- GMIT lecture content for Network Technologies and Object-Oriented Software Development:
Socket Programming Examples: https://web.microsoftstream.com/video/0d5c256b-5afa-4ca6-a3a4-6c4a37c44706
- Threads: https://web.microsoftstream.com/video/64dcde32-67ae-4d4e-954e-273ec33413d1
 
