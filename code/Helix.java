class Helix
{

  private float size;

  private Strand leftStrand;
  private Strand rightStrand;

  /* Constructor definition */
  public Helix(float size)
  {
    this.size = size;
    this.createStrands();
  }

  /* Function definition */
  private void createStrands()
  {
    var phase = 0f;
    var strandColor = color(95, 75, 139);
    this.leftStrand = new Strand(this.size, phase, strandColor);

    phase = PI / 1.44;
    strandColor = color(230, 154, 141);
    this.rightStrand = new Strand(this.size, phase, strandColor);
  }

  public void spin()
  {
    this.leftStrand.spin();
    this.rightStrand.spin();
  }

  public void render()
  {
    pushMatrix();
    translate(width / 2, height);
    rotate(-PI / 2);

    this.leftStrand.render();
    this.rightStrand.render();
    this.connectStrands();
    popMatrix();
  }

  private void connectStrands()
  {
    noFill();
    stroke(0, 83, 156);
    strokeWeight(2);

    for (int n = 0; n < this.leftStrand.nucleotides.size(); n++)
    {
      var isMultipleOf3 = (n % 3 == 0);
      if (isMultipleOf3)
      {
        var leftNucleotide = this.leftStrand.nucleotides.get(n);
        var rightNucleotide = this.rightStrand.nucleotides.get(n);

        var sX = leftNucleotide.position.x;
        var sY = leftNucleotide.position.y;
        var start = new PVector(sX, sY);

        var eX = rightNucleotide.position.x;
        var eY = rightNucleotide.position.y;
        var end = new PVector(eX, eY);

        line(start.x, start.y, end.x, end.y);
      }
    }
  }
}
