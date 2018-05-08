# Top left corner is (0,0)

import pygame

class Player1():
    # has x location, y location, score, width, height
    def __init__(self):
        self.x_loc = 12
        self.score = 0
        self.width = 8
        self.height = 64
        self.y_loc = WINDOW_HEIGHT / 2 - (self.height / 2)  # for offset so it's in center

    def drawPlayer(self):
        pygame.draw.rect(screen, (245, 37, 85), (self.x_loc, self.y_loc, self.width, self.height))

    def move(self):
        # check if player is at border of frame
        if self.y_loc <= 0:
            self.y_loc = 0
        elif self.y_loc >= WINDOW_HEIGHT - self.height:
            self.y_loc = WINDOW_HEIGHT - self.height

        # check what key player pressed
        keyPressed = pygame.key.get_pressed()
        if keyPressed[pygame.K_s]:      # wants to move down
            self.y_loc = self.y_loc + MOVE
        elif keyPressed[pygame.K_w]:    # wants to move up
            self.y_loc = self.y_loc - MOVE

    def drawScore(self):
        font = pygame.font.Font(None, 64)
        p1_blit = font.render(str(self.score), 1, (255, 255, 255))
        screen.blit(p1_blit, (32, 20))
        if self.score == 5:
            screen.fill((0, 0, 0))
            font = pygame.font.Font(None, 64)
            font2 = pygame.font.Font(None, 36)
            blit = font.render("Player 1 Wins!", 1, (255, 255, 255))
            blit2 = font2.render("  Press q to quit  ", 1, (255, 255, 255))
            blit3 = font2.render("Press r to restart ", 1, (255, 255, 255))
            screen.blit(blit, (WINDOW_WIDTH / 2 - 150, WINDOW_HEIGHT / 2 - 20))
            screen.blit(blit2, (WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT - 70))
            screen.blit(blit3, (WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT - 40))
            pygame.display.flip()
            event = pygame.event.wait()
            if event.type == pygame.QUIT:
                pygame.quit()
                exit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    exit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_r:
                    start()

class Player2():
    # has x location, y location, score, width, height
    def __init__(self):
        self.score = 0
        self.width = 8
        self.height = 64
        self.x_loc = WINDOW_WIDTH - 12 - self.width
        self.y_loc = WINDOW_HEIGHT / 2 - (self.height / 2)  # for offset so it's in center

    def drawPlayer(self):
        pygame.draw.rect(screen, (245, 37, 85), (self.x_loc, self.y_loc, self.width, self.height))

    def move(self):
        # check if player is at border of frame
        if self.y_loc <= 0:
            self.y_loc = 0
        elif self.y_loc >= WINDOW_HEIGHT - self.height:
            self.y_loc = WINDOW_HEIGHT - self.height

        # check what key player pressed
        keyPressed = pygame.key.get_pressed()
        if keyPressed[pygame.K_DOWN]:
            self.y_loc = self.y_loc + MOVE
        elif keyPressed[pygame.K_UP]:
            self.y_loc = self.y_loc - MOVE

    def drawScore(self):
        font = pygame.font.Font(None, 64)
        p2_blit = font.render(str(self.score), 1, (255, 255, 255))
        screen.blit(p2_blit, (WINDOW_WIDTH - 32 - 24, 20))  # 32 to match, 24 because it looks closest
        if self.score == 5:
            screen.fill((0, 0, 0))
            font = pygame.font.Font(None, 64)
            font2 = pygame.font.Font(None, 36)
            blit = font.render("Player 2 Wins!", 1, (255, 255, 255))
            blit2 = font2.render("  Press q to quit  ", 1, (255, 255, 255))
            blit3 = font2.render("Press r to restart ", 1, (255, 255, 255))
            screen.blit(blit, (WINDOW_WIDTH / 2 - 150, WINDOW_HEIGHT / 2 - 20))
            screen.blit(blit2, (WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT - 70))
            screen.blit(blit3, (WINDOW_WIDTH / 2 - 100, WINDOW_HEIGHT - 40))
            pygame.display.flip()
            event = pygame.event.wait()
            if event.type == pygame.QUIT:
                pygame.quit()
                exit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_q:
                    exit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_r:
                    start()


class Ball():
    # has x location, y location, x movement, y movement
    def __init__(self):
        self.x_loc = WINDOW_WIDTH / 2
        self.y_loc = WINDOW_HEIGHT / 2
        self.x_move = MOVE
        self.y_move = MOVE

    def move(self):
        # moves right when called, then does all collision checks
        self.x_loc = self.x_loc + self.x_move
        self.y_loc = self.y_loc + self.y_move

        # check of ball hit bottom wall
        if self.y_loc >= WINDOW_HEIGHT:
            self.y_move = -1 * self.y_move

        # check if ball hit top wall
        if self.y_loc <= 0:
            self.y_move = -1 * self.y_move

        # check if ball hit left wall - P2 point
        if self.x_loc <= 0:
            player2.score = player2.score + 1
            self.__init__()                     # reinitialize ball to start in middle
            self.x_move = self.x_move * - 1     # change ball direction to go towards P1

        # check if ball hit right wall - P1 point
        if self.x_loc >= WINDOW_WIDTH:
            player1.score = player1.score + 1
            self.__init__()                      # reinitialize ball to start in middle

        # check if ball hits P1
        for n in range(-5, player1.height):
            if self.y_loc == n + player1.y_loc:                 # ball y == player y + increment
                if self.x_loc <= player1.x_loc + player1.width:
                    self.x_move = self.x_move * - 1             # change ball direction
                    break
            n = n + 1

        # check if ball hits P2
        for n in range(-5, player2.height):
            if self.y_loc == n + player2.y_loc:                 # ball y == player y + increment
                if self.x_loc >= player2.x_loc - player2.width:
                    self.x_move = self.x_move * - 1             # change ball direction
                    break
            n = n + 1

    def drawBall(self):
        pygame.draw.circle(screen, (74, 235, 12), (int(self.x_loc), int(self.y_loc)), 5)

def game():
    done = False
    while not done:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                done = True
                exit()

        # move then clear screen then redraw in new spot
        player1.move()
        player2.move()
        ball.move()

        screen.fill((0, 0, 0))

        ball.drawBall()
        player1.drawPlayer()
        player1.drawScore()
        player2.drawPlayer()
        player2.drawScore()

        pygame.display.flip()


WINDOW_WIDTH = 800
WINDOW_HEIGHT = 600
MOVE = 5    # move speed for everything for easy changing
screen = pygame.display.set_mode((WINDOW_WIDTH, WINDOW_HEIGHT))
pygame.init()
done = False

player1 = Player1()
player2 = Player2()
ball = Ball()

def start():
    player1.__init__()
    player2.__init__()
    ball.__init__()
    while True:
        screen.fill((0, 0, 0))
        player1.drawPlayer()
        player2.drawPlayer()
        player1.drawScore()
        player2.drawScore()

        font = pygame.font.Font(None, 64)
        font2 = pygame.font.Font(None, 36)
        blit = font.render(" Press Space to Start ", 1, (255, 255, 255))
        blit2 = font2.render("  Player 1 keys w & s  ", 1, (255, 255, 255))
        blit3 = font2.render("Player 2 keys up & down", 1, (255, 255, 255))
        screen.blit(blit, (WINDOW_WIDTH / 2 - 220, WINDOW_HEIGHT / 2 - 20))
        screen.blit(blit2, (WINDOW_WIDTH / 2 - 125, WINDOW_HEIGHT - 70))
        screen.blit(blit3, (WINDOW_WIDTH / 2 - 146, WINDOW_HEIGHT - 40))
        pygame.display.flip()

        event = pygame.event.wait()
        if event.type == pygame.QUIT:
            pygame.quit()
            exit()
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_SPACE:
                game()

start()