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
  
  void orbit() {
   angle = angle + orbitSpeed; 
    if (planets != null) {
      for (int i = 0; i < planets.length; i++) {
        planets[i].orbit();
      }
    } 
  }
  void moons(int total, int level, PImage moonImg) {
    planets = new Planet[total];
    for (int i = 0; i < planets.length; i++) {
      diameter[i] = map(diameter[i],1.516,43.441,7.0,20.0);
      float r = (radius/2)/(level*10)*diameter[i];
      float d = ((radius)/2)*((i+1)*4);
      float o =0.2/(r/2);//random(-0.01,0.01)*(level*(level+1));
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
  
  void show(int mult){
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
