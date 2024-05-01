Helix dna;

void setup()
{
  surface.setTitle("DNA Helix");
  surface.setResizable(false);
  surface.setLocation(displayWidth / 3, floor(0.1 * displayHeight));
  
  createHelix();

  size(660, 660);
}

void createHelix()
{
  var size = 420;
  dna = new Helix(size);
}

void draw()
{
  background(0);

  dna.spin();
  dna.render();
}
