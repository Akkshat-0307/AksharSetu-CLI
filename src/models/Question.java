package models;

public class Question {
    private String text;
    private String[] options;
    private int correctOption;
    private String topic;

    public Question(String text, String[] options, int correctOption, String topic) {
        this.text = text;
        this.options = options;
        this.correctOption = correctOption;
        this.topic = topic;
    }

    public void displayQuestion() {
        System.out.println("\n" + text);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int answer) {
        return answer == correctOption;
    }

    public String getTopic() {
        return topic;
    }
    public int getOptionsLength() {
        return options.length;
    }
}