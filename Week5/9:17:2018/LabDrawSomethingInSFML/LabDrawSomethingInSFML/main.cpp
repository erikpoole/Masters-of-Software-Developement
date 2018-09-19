//
//  main.cpp
//  LabDrawSomethingInSFML
//
//  Created by Erik Poole on 9/17/18.
//  Copyright © 2018 ErikPoole. All rights reserved.
//

#include <iostream>
#include <SFML/Graphics.hpp>
#include <SFML/Window.hpp>
#include <SFML/System.hpp>


int main(int argc, const char * argv[]) {

    sf::RenderWindow window(sf::VideoMode(800,600), "My window");
    
    while (window.isOpen()) {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
        }
        window.clear(sf::Color::Black);
        
        sf::CircleShape shape(100);
        shape.setFillColor(sf::Color(100,100,100));
        window.draw(shape);
        
        window.display();
    }
}
