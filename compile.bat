@echo off
echo Compiling Java files...

javac -d bin src/com/player_app/*.java src/com/room_app/*.java src/com/enemy_app/*.java src/com/game_app/*.java 

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed.
    exit /b %ERRORLEVEL%
)

echo Running the program...
java -cp bin com.game_app.Game
