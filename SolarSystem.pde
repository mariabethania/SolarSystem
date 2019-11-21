import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.effects.*;
import ddf.minim.signals.*;
import ddf.minim.spi.*;
import ddf.minim.ugens.*;
import peasy.*;

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
float[] diameter = {1.516,3.760,3.958,2.106,43.441,36.184,15.759,15.299,0};


float sol,mercurio,venus,tierra,marte,jupiter,saturno,urano,neptuno,luna;

void setup() {
  size(1350,700,P3D);
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
  
  sun = new Planet(100,0,0.0,sunText);
  sun.moons(9,1,textures[8]);
   
  //song.play();
}

void draw() {
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

void keyPressed() {
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
