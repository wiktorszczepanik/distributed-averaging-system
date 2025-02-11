## Distributed Averaging System (DAS)

### Overview

This application implements a **Distributed Averaging System (DAS)**. 
It operates in a network environment, where multiple instances of the application communicate over *UDP* to calculate and share an average value. 
The system works in two modes: **Server** and **Client**, with automatic mode selection based on the current system state.
Project was developed as part of the SKJ course at PJATK.

### How It Works

The application consists of a single program that implements the `DAS` class. 
It can be run with the following command:

```Bash
java DAS <port> <number>
```

Where:
- `<port>` is the *UDP* port number the application will attempt to bind to.
- `<number>` is an integer parameter passed to the application.

### Server Mode
When the application successfully opens the specified port, it enters **Server mode**.

The server process:
- Remembers the `<number>` value passed as a parameter.
- Continuously listens for incoming messages on the specified UDP port.
- When it receives a value:
    - If the value is non-zero or non-negative one, it logs and stores the value.
    - If the value is `0`, it computes the average of all non-zero numbers received so far, including `<number>`, prints the result, and broadcasts it to other machines on the network.
    - If the value is `-1`, it logs the value, sends `-1` to all machines, and then terminates the process.

### Client Mode
If the application cannot open the specified port because it is already in use (indicating another server instance is running), it enters **Client mode**.

The client process:
- Generates a random *UDP* port for communication.
- Sends a message containing the `<number>` value to the server process running on the same machine at the specified `<port>`.
- After sending the message, the process terminates.

## Conclusion
**DAS** demonstrates basic UDP communication and peer-to-peer data sharing, with automatic role assignment for server and client processes.
