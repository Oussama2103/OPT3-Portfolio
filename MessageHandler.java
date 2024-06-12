abstract class MessageHandler {
    public void processMessageTemplate(Bericht bericht) {
        if (validateMessage(bericht)) {
            processSpecificMessage(bericht);
        }
    }

    protected abstract boolean validateMessage(Bericht bericht);

    protected abstract void processSpecificMessage(Bericht bericht);
}