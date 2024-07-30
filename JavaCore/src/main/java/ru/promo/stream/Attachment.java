package ru.promo.stream;

public class Attachment<T> {
    private AttachmentType type;
    private T content;

    public Attachment(AttachmentType type, T content) {
        this.type = type;
        this.content = content;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
