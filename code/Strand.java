class Strand
{

  private PVector bounds;

  private float phase;
  private float amplitude;
  private float frequency;

  private int precision;
  private float pointSpacing;
  private float deltaPhase;

  private ArrayList<Nucleotide> nucleotides;

  /* Constructor definition */
  public Strand(float size, float phase, color strandColor)
  {
    var start = (width - size) / 2;
    var end = start + size;
    this.bounds = new PVector(start, end);

    this.phase = phase;
    this.amplitude = 0.096 * height;
    this.frequency = PI / 600;

    this.precision = 123;
    this.pointSpacing = width / this.precision;
    this.deltaPhase = PI / 120;

    this.createStrand(strandColor);
  }

  /* Function definition */
  private void createStrand(color strandColor)
  {
    this.nucleotides = new ArrayList<Nucleotide>();

    for (float x = this.bounds.x; x < this.bounds.y; x += this.pointSpacing)
    {
      var currentPhase = (this.phase + this.deltaPhase);
      var theta = currentPhase + TAU * x * this.frequency;

      var posX = x;
      var posY = map(sin(theta), -1, 1, -this.amplitude, this.amplitude);
      var position = new PVector(posX, posY);
      var radius = 9;

      this.nucleotides.add(new Nucleotide(position, radius, strandColor));
    }
  }

  public void spin()
  {
    if (this.nucleotides != null)
    {
      this.updateNucleotides();
      this.phase += this.deltaPhase;
    }
  }

  private void updateNucleotides()
  {
    var p = 0;
    for (float x = this.bounds.x; x < this.bounds.y; x += this.pointSpacing)
    {
      var currentPhase = (this.phase + this.deltaPhase);
      var theta = currentPhase + TAU * x * this.frequency;

      var posX = x;
      var posY = map(sin(theta), -1, 1, -this.amplitude, this.amplitude);
      var position = new PVector(posX, posY);
      var nucleotide = this.nucleotides.get(p);

      nucleotide.spin(position);
      p++;
    }
  }

  public void render()
  {
    if (this.nucleotides != null)
    {
      for (var nucleotide : this.nucleotides)
        nucleotide.render();
    }
  }
}
