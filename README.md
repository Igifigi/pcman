# PC-MAN

A classic Pac-Man style arcade game built in Java Swing.

You are PCMAN, and you must collect all the orbs on the map while being hunted by evil Linux distributions!

## 🎮 How to play

* **Move:** Use **W, A, S, D** keys.
* **Goal:** Collect all the small orbs to win.
* **Power orbs:** Collect the large orbs to become powerful. While active, you can eat the enemies!
* **Lives:** You have 3 lives. Touching an enemy when you are not powered-up will cost you one life.

## 🐧 The enemies

* **Ubuntu** chases you directly
* **Mint** Tries to cut you off
* **Gentoo** Chases you, but runs away if you get too close
* **Arch** Tries to flank you

## 🚀 How to run

A runner script is provided to compile and launch the game.

1.  Make the script executable (for Linux/macOS only):
    ```bash
    chmod +x run.sh
    ```

2.  Run the script:
    ```bash
    ./run.sh
    ./run.bat (for Windows)
    ```



The script will build the game in a `tmp` folder and start it automatically.
