/*
 * Transferring large sized file through SocketChannel
 * 1. Create a server class named FileReceiver
 * 2. Client a client class named FileSender
 * 3. Demo to send a large file
 * 
 * Thank you for your watching
 * lehuy2706@gmail.com
 *
*/
package filetransfer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

public class FileReceiver {

	public static void main(String[] args) throws IOException {
		FileReceiver server = new FileReceiver();
		SocketChannel socketChannel = server.createServerSocketChannel();
		server.readFileFromSocketChannel(socketChannel);

	}

	private void readFileFromSocketChannel(SocketChannel socketChannel) throws IOException {
		//Try to create a new file
		Path path = Paths.get("c:\\1000 Listening Comprehension Practice Test Items for the New TOEIC Test - Jim Lee.pdf");
		FileChannel fileChannel = FileChannel.open(path,
				EnumSet.of(StandardOpenOption.CREATE,
						StandardOpenOption.TRUNCATE_EXISTING,
						StandardOpenOption.WRITE)
				);
		//Allocate a ByteBuffer
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (socketChannel.read(buffer) > 0) {
			buffer.flip();
			fileChannel.write(buffer);
			buffer.clear();
		}
		fileChannel.close();
		System.out.println("Receving file successfully!");
		socketChannel.close();
	}

	private SocketChannel createServerSocketChannel() throws IOException {
		ServerSocketChannel serverSocket = null;
		SocketChannel client = null;
		serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(9000));
		client = serverSocket.accept();
		
		System.out.println("connection established .." + client.getRemoteAddress());
		return client;
	}

}
