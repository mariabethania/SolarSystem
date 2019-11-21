import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 
import peasy.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class SolarSystem extends PApplet {









Minim       minim;
AudioInput input;
AudioPlayer song;
FFT         fft;

PeasyCam cam;
Planet sun;
PImage sky;
PImage sunText;
PImage[] textures = new PImage[10];
float[] music = new float[20];
float rY;
float[] diameter = {1.516f,3.760f,3.958f,2.106f,43.441f,36.184f,15.759f,15.299f,0};


float sol,mercurio,venus,tierra,marte,jupiter,saturno,urano,neptuno,luna;

public void setup() {
  
  //fullScreen();
  cam = new PeasyCam(this,0,0,0,2000);
  minim = new Minim(this);
  song = minim.loadFile("1. let it Be - OsoX.mp3", 1024);
//  input = minim.getLineIn();
  //fft = new FFT(input.bufferSize(), input.sampleRate());
  fft = new FFT(song.bufferSize(), song.sampleRate());

  
  sky = loadImage("milky_way_1350x700.jpg");
  sunText = loadImage("2k_sun 2.jpg");
  textures[0] = loadImage("2k_mercury.jpg");
  textures[1] = loadImage("2k_venus_atmosphere.jpg");
  textures[2] = loadImage("2k_earth_daymap.jpg");
  textures[3] = loadImage("2k_mars.jpg");
  textures[4] = loadImage("2k_jupiter.jpg");
  textures[5] = loadImage("2k_saturn.jpg");
  textures[6] = loadImage("2k_uranus.jpg");
  textures[7] = loadImage("2k_neptune.jpg");
  textures[8] = loadImage("2k_moon.jpg");
  textures[9] = loadImage("2k_saturn_ring_alpha.png");
  
  sun = new Planet(100,0,0.0f,sunText);
  sun.moons(9,1,textures[8]);
   
  //song.play();
}

public void draw() {
  background(sky);
  //fft.forward(input.mix);
  fft.forward(song.mix);
  //lights();
ambientLight(50, 50, 50);
spotLight(255, 255, 255, 0, 0,300,0,0,0,0,0);
//spotLight(255, 255, 255, 0, 0,-150,0,0,0,0,0);
//directionalLight(255, 255, 255, 0, 0,1);

//pointLight(255, 255, 255, 0, 0,0);
//rY += 0.005;
//  rotateY(rY);
  //translate(width/2,height/2,0);
  sun.show(1);
  //fill(255,255,180,10);
  //sphere(150);
  //fill(255,255,180,20);
  //sphere(130);
  fill(255,255,180,70);
  sphere(130);
  noFill();
  sun.orbit();
  //camera();
}

public void keyPressed() {
 if (keyCode == ENTER) {
  if (song.isPlaying()) {
    song.pause();
  }   
  else
  {
    song.play();
  }
 }
}
class Planet {
float radius;
float angle;
float distance;
Planet[] planets;
float orbitSpeed;
float col;
PVector v;
PShape globe;
int i;
  Planet(float r, float d, float o, PImage img){
    v = PVector.random3D();
    v.x = abs(v.x);
    v.x = abs(v.x);
    v.x = abs(v.x);

    radius = r;
    distance = d;
    v.mult(distance);
    angle = random(TWO_PI);
    orbitSpeed = o;
    noStroke();
    //fill(255,0,0);
    noFill();
    globe = createShape(SPHERE,radius+music[i]);
    globe.setTexture(img);
  }
  
  public void orbit() {
   angle = angle + orbitSpeed; 
    if (planets != null) {
      for (int i = 0; i < planets.length; i++) {
        planets[i].orbit();
      }
    } 
  }
  public void moons(int total, int level, PImage moonImg) {
    planets = new Planet[total];
    for (int i = 0; i < planets.length; i++) {
      diameter[i] = map(diameter[i],1.516f,43.441f,7.0f,20.0f);
      float r = (radius/2)/(level*10)*diameter[i];
      float d = ((radius)/2)*((i+1)*4);
      float o =0.2f/(r/2);//random(-0.01,0.01)*(level*(level+1));
//println(r);
      
//fill(random(255),random(255),random(255));
      planets[i] = new Planet(r, d, o,textures[i]);
      if (level < 2 ) {
        int num = 1;//int(random(0,4));
//fill(random(255),random(255),random(255));
      planets[i].moons(num,level+1,moonImg);
      }  
    }
  }
  
  public void show(int mult){
  for (int i = 19; i > 0; i--) {
    //sol += 
    music[i] = fft.getBand(i);
}

    pushMatrix();
    noStroke();
    fill(255,50);
    PVector v2 = new PVector(0,0,1);
    PVector p = v.cross(v2);
    rotate(angle,p.x,p.y,p.z);
    //stroke(255,30);
    //line(0,0,0,v.x,v.y,v.z);
    //stroke(255,255);
    //point(v.x,v.y,v.z);
    noStroke();
    translate(v.x,v.y,v.z);
    shape(globe);
    //noStroke();
fill(150,190,255,150);
    sphere((radius-3)+(music[i]));
    rotateX(2);    
    ellipse(0,0,radius*mult,radius*mult);
    fill(0,100);
    ellipse(0,0,radius*(mult-2),radius*(mult-2));
    
rotateX(-2);    
    if (planets != null) {
    for (i = 0; i < planets.length; i++) {
      if (i == 5) {
       mult = 5; 
      } else {mult = 1;}
    planets[i].show(mult);
    //radius /= 4;
    //planets[i].moons(3,2);    
    //music[10] = radius;
    }
  }
    popMatrix();
  
}
}
  public void settings() {  size(1350,700,P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "SolarSystem" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
