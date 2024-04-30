class DNAHelix
{

  private float size;

  private Strand leftStrand;
  private Strand rightStrand;

  /* Constructor definition */
  public DNAHelix(float size)
  {
    this.size = size;
    this.initializeStrands();
  }

  /* Function definition */
  private void initializeStrands()
  {
    var size = this.size;
    var amplitude = 0.09 * height;
    var frequency = PI / 600;
    var precision = 99;

    // Left strand initialization.
    var phase = 0f;
    var strandColor = color(120, 60, 90);
    this.leftStrand = new Strand(size, phase, amplitude,
                                 frequency, precision, strandColor);

    // Right strand initialization.
    phase = PI / 1.5;
    strandColor = color(90, 60, 120);
    this.rightStrand = new Strand(size, phase, amplitude,
                                  frequency, precision, strandColor);
  }

  public void spin()
  {
    leftStrand.spin();
    rightStrand.spin();
  }

  public void show()
  {
    pushMatrix();
    translate(width / 2, height);
    rotate(-PI / 2);

    leftStrand.show();
    rightStrand.show();
    this.connectStrands();
    popMatrix();
  }

  private void connectStrands()
  {
    noFill();
    stroke(30, 60, 120);
    strokeWeight(2);

    var totalNucleotides = leftStrand.nucleotides.size();
    for (int n = 0; n < totalNucleotides; n++)
    {
      var isMultipleOf3 = (n % 3 == 0);
      if (isMultipleOf3)
      {
        var leftNucleotide = leftStrand.nucleotides.get(n);
        var rightNucleotide = rightStrand.nucleotides.get(n);

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
