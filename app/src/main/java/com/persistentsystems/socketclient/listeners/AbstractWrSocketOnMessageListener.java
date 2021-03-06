package com.persistentsystems.socketclient.listeners;

import com.neovisionaries.ws.client.*;

import java.util.List;
import java.util.Map;

/**
 * This is an abstract class that is implemented by a child class, mainly {@link com.persistentsystems.socketclient.sockets.WrBlockingSocket},
 * overriding the listeners they need.
 * <i>Note: Documentation here is copied from the Neovisionaries websocket library</i>
 */
public abstract class AbstractWrSocketOnMessageListener implements WebSocketListener {
    /**
     * Called after the state of the WebSocket changed.
     *
     * @param xWebSocket The WebSocket.
     * @param xNewState  The new state of the WebSocket.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     **/
    @Override
    public void onStateChanged(WebSocket xWebSocket, WebSocketState xNewState) throws Exception {

    }

    /**
     * Called after the opening handshake of the WebSocket connection succeeded.
     *
     * @param xWebSocket The WebSsocket.
     * @param xHeaders   HTTP headers received from the server. Keys of the map are
     *                   HTTP header names such as {@code "Sec-WebSocket-Accept"}.
     *                   Note that the comparator used by the map is {@link
     *                   String#CASE_INSENSITIVE_ORDER}.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onConnected(WebSocket xWebSocket, Map<String, List<String>> xHeaders) throws Exception {

    }

    /**
     * Called when {@link WebSocket#connectAsynchronously()} failed.
     *
     * <p>
     * Note that this method is called only when {@code connectAsynchronously()}
     * was used and the {@link WebSocket#connect() connect()} executed in the
     * background thread failed. Neither direct synchronous {@code connect()}
     * nor {@link WebSocket#connect(java.util.concurrent.ExecutorService)
     * connect(ExecutorService)} will trigger this callback method.
     * </p>
     *
     * @param xWebSocket The WebSocket.
     * @param xExcept    The exception thrown by {@link WebSocket#connect() connect()}
     *                   method.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onConnectError(WebSocket xWebSocket, WebSocketException xExcept) throws Exception {

    }

    /**
     * Called after the WebSocket connection was closed.
     *
     * @param xWebSocket        The WebSocket.
     * @param xServerCloseFrame The <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1"
     *                          >close frame</a> which the server sent to this client.
     *                          This may be {@code null}.
     * @param xClientCloseFrame The <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1"
     *                          >close frame</a> which this client sent to the server.
     *                          This may be {@code null}.
     * @param xByServer         {@code true} if the closing handshake was started by the server.
     *                          {@code false} if the closing handshake was started by the client.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onDisconnected(WebSocket xWebSocket, WebSocketFrame xServerCloseFrame, WebSocketFrame xClientCloseFrame, boolean xByServer) throws Exception {

    }

    /**
     * Called when a frame was received. This method is called before
     * an <code>on<i>Xxx</i>Frame</code> method is called.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a continuation frame (opcode = 0x0) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The continuation frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onContinuationFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a text frame (opcode = 0x1) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The text frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onTextFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a binary frame (opcode = 0x2) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The binary frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onBinaryFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1"
     * >close frame</a> (opcode = 0x8) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1">close frame</a>.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onCloseFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.2"
     * >ping frame</a> (opcode = 0x9) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The <a href="https://tools.ietf.org/html/rfc6455#section-5.5.2">ping frame</a>.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onPingFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.3"
     * >pong frame</a> (opcode = 0xA) was received.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The <a href="https://tools.ietf.org/html/rfc6455#section-5.5.3">pong frame</a>.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onPongFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a text message was received.
     *
     * @param xWebSocket The WebSocket.
     * @param xText      The text message.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    abstract public void onTextMessage(WebSocket xWebSocket, String xText) throws Exception;

    /**
     * Called when a binary message was received.
     *
     * @param xWebSocket The WebSocket.
     * @param xBytes     The binary message.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onBinaryMessage(WebSocket xWebSocket, byte[] xBytes) throws Exception {

    }

    /**
     * Called before a WebSocket frame is sent.
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The WebSocket frame to be sent.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     * @since 1.15
     */
    @Override
    public void onSendingFrame(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a WebSocket frame was sent to the server
     * (but not flushed yet).
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The sent frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onFrameSent(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when a WebSocket frame was not sent to the server
     * because a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1"
     * >close frame</a> has already been sent.
     *
     * <p>
     * Note that {@code onFrameUnsent} is not called when {@link
     * #onSendError(WebSocket, WebSocketException, WebSocketFrame)
     * onSendError} is called.
     * </p>
     *
     * @param xWebSocket      The WebSocket.
     * @param xWebSocketFrame The unsent frame.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onFrameUnsent(WebSocket xWebSocket, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called between after a thread is created and before the thread's
     * {@code start()} method is called.
     *
     * @param xWebSocket  The WebSocket.
     * @param xThreadType The thread type.
     * @param xThread     The newly created thread instance.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onThreadCreated(WebSocket xWebSocket, ThreadType xThreadType, Thread xThread) throws Exception {

    }

    /**
     * Called at the very beginning of the thread's {@code run()} method implementation.
     *
     * @param xWebSocket  The WebSocket.
     * @param xThreadType The thread type.
     * @param xThread     The thread instance.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onThreadStarted(WebSocket xWebSocket, ThreadType xThreadType, Thread xThread) throws Exception {

    }

    /**
     * Called at the very end of the thread's {@code run()} method implementation.
     *
     * @param xWebSocket  The WebSocket.
     * @param xThreadType The thread type.
     * @param xThread     The thread instance.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onThreadStopping(WebSocket xWebSocket, ThreadType xThreadType, Thread xThread) throws Exception {

    }

    /**
     * Call when an error occurred. This method is called before
     * an <code>on<i>Xxx</i>Error</code> method is called.
     *
     * @param xWebSocket The WebSocket.
     * @param xCause     An exception that represents the error.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onError(WebSocket xWebSocket, WebSocketException xCause) throws Exception {

    }

    /**
     * Called when a WebSocket frame failed to be read from the WebSocket.
     *
     * <p>
     * Some WebSocket server implementations close a WebSocket connection without sending
     * a <a href="https://tools.ietf.org/html/rfc6455#section-5.5.1">close frame</a> to a
     * client in some cases. Strictly speaking, this behavior is a violation against the
     * specification (<a href="https://tools.ietf.org/html/rfc6455">RFC 6455</a>). However,
     * this library has allowed the behavior by default since the version 1.29. Even if
     * the end of the input stream of a WebSocket connection were reached without a
     * close frame being received, it would trigger neither {@link #onError(WebSocket,
     * WebSocketException) onError()} method nor {@link #onFrameError(WebSocket,
     * WebSocketException, WebSocketFrame) onFrameError()} method. If you want to make
     * this library report an error in the case, pass {@code false} to {@link
     * WebSocket#setMissingCloseFrameAllowed(boolean)} method.
     * </p>
     *
     * @param xWebSocket      The WebSocket.
     * @param xCause          An exception that represents the error. When the error occurred
     *                        because of {@link java.io.InterruptedIOException InterruptedIOException},
     *                        {@code exception.getError()} returns {@link WebSocketError#INTERRUPTED_IN_READING}.
     *                        For other IO errors, {@code exception.getError()} returns {@link
     *                        WebSocketError#IO_ERROR_IN_READING}. Other error codes denote
     *                        protocol errors, which imply that some bugs may exist in either
     *                        or both of the client-side and the server-side implementations.
     * @param xWebSocketFrame The WebSocket frame. If this is not {@code null}, it means that
     *                        verification of the frame failed.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onFrameError(WebSocket xWebSocket, WebSocketException xCause, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when it failed to concatenate payloads of multiple frames
     * to construct a message. The reason of the failure is probably
     * out-of-memory.
     *
     * @param xWebSocket The WebSocket.
     * @param xCause     An exception that represents the error.
     * @param xList      The list of frames that form a message. The first element
     *                   is either a text frame and a binary frame, and the other
     *                   frames are continuation frames.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onMessageError(WebSocket xWebSocket, WebSocketException xCause, List<WebSocketFrame> xList) throws Exception {

    }

    /**
     * Called when a message failed to be decompressed.
     *
     * @param xWebSocket The WebSocket.
     * @param xCause     An exception that represents the error.
     * @param xBytes     The compressed message that failed to be decompressed.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     * @since 1.16
     */
    @Override
    public void onMessageDecompressionError(WebSocket xWebSocket, WebSocketException xCause, byte[] xBytes) throws Exception {

    }

    /**
     * Called when it failed to convert payload data into a string.
     * The reason of the failure is probably out-of-memory.
     *
     * @param xWebSocket The WebSocket.
     * @param xCause     An exception that represents the error.
     * @param xBytes     The payload data that failed to be converted to a string.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onTextMessageError(WebSocket xWebSocket, WebSocketException xCause, byte[] xBytes) throws Exception {

    }

    /**
     * Called when an error occurred when a frame was tried to be sent
     * to the server.
     *
     * @param xWebSocket      The WebSocket.
     * @param xCause          An exception that represents the error.
     * @param xWebSocketFrame The frame which was tried to be sent. This is {@code null}
     *                        when the error code of the exception is {@link
     *                        WebSocketError#FLUSH_ERROR FLUSH_ERROR}.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onSendError(WebSocket xWebSocket, WebSocketException xCause, WebSocketFrame xWebSocketFrame) throws Exception {

    }

    /**
     * Called when an uncaught throwable was detected in either the
     * reading thread (which reads frames from the server) or the
     * writing thread (which sends frames to the server).
     *
     * @param xWebSocket The WebSocket.
     * @param xCause     The cause of the error.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     */
    @Override
    public void onUnexpectedError(WebSocket xWebSocket, WebSocketException xCause) throws Exception {

    }

    /**
     * Called when an <code>on<i>Xxx</i>()</code> method threw a {@code Throwable}.
     *
     * @param xWebSocket The WebSocket.
     * @param xThrowable The {@code Throwable} an <code>on<i>Xxx</i></code> method threw.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is just ignored.
     */
    @Override
    public void handleCallbackError(WebSocket xWebSocket, Throwable xThrowable) throws Exception {

    }

    /**
     * Called before an opening handshake is sent to the server.
     *
     * @param xWebSocket   The WebSocket.
     * @param xRequestLine The request line. For example, {@code "GET /chat HTTP/1.1"}.
     * @param xHeaders     The HTTP headers.
     * @throws Exception An exception thrown by an implementation of this method.
     *                   The exception is passed to {@link #handleCallbackError(WebSocket, Throwable)}.
     * @since 1.21
     */
    @Override
    public void onSendingHandshake(WebSocket xWebSocket, String xRequestLine, List<String[]> xHeaders) throws Exception {

    }
}
