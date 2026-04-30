package com.example;

public class PremiumUser extends User {
    public int credits = 100;

    public void generate_image() {
        this.credits -= 5;
        System.out.println("Image generated successfully!");
    }

    public void use_ai_prompt() {
        if (this.credits > 5) {
            this.generate_image();
        }
    }
}
