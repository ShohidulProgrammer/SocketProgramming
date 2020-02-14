package filetransfer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSender {

	public static void main(String[] args) throws IOException {
		FileSender client = new FileSender();
		SocketChannel socketChannel = client.CreateChannel();
		client.sendFile(socketChannel);

	}

	private void sendFile(SocketChannel socketChannel) throws IOException {

		//Read a file from disk. Its filesize is 54.3 MB (57,006,053 bytes)
		// receive the same size                 54.3 MB (57,006,053 bytes)
		Path path = Paths.get("d:\\eng\\1000 Listening Comprehension Practice Test Items for the New TOEIC Test - Jim Lee.pdf");
		FileChannel inChannel = FileChannel.open(path);
		
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		while (inChannel.read(buffer) > 0) {
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}
		socketChannel.close();
	}

	private SocketChannel CreateChannel() throws IOException {
		//Remember that is code only works on blocking mode
		SocketChannel socketChannel = SocketChannel.open();
		
		//we don't need call this function as default value of blocking mode = true
		socketChannel.configureBlocking(true);
		
		SocketAddress sockAddr = new InetSocketAddress("localhost", 9000);
		socketChannel.connect(sockAddr);
		return socketChannel;
	}

}
