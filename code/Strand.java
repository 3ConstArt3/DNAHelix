class Strand
{

  private PVector vertices;

  private float phase;
  private float amplitude;
  private float frequency;

  private int precision;
  private float pointSpacing;
  private float deltaPhase;

  private color strandColor;
  private ArrayList<Nucleotide> nucleotides;

  /* Constructor definition */
  public Strand(float size, float phase, float amplitude,
                float frequency, int precision, color strandColor)
  {
    var start = (width - size) / 2;
    var end = start + size;
    this.vertices = new PVector(start, end);

    this.phase = phase;
    this.amplitude = amplitude;
    this.frequency = frequency;

    this.precision = precision;
    this.pointSpacing = (this.precision > 0 ? width / this.precision : 0);
    this.deltaPhase = PI / 120;

    this.strandColor = color(strandColor);
    this.createStrand();
  }

  /* Function definition */
  private void createStrand()
  {
    this.nucleotides = new ArrayList<Nucleotide>();

    var start = this.vertices.x;
    var end = this.vertices.y;
    for (float x = start; x < end; x += this.pointSpacing)
    {
      var currentPhase = (this.phase + this.deltaPhase);
      var theta = currentPhase + TAU * x * this.frequency;
      var y = map(sin(theta), -1, 1, -this.amplitude, this.amplitude);

      var position = new PVector(x, y);
      var radius = 9;
      var fillColor = this.strandColor;

      this.nucleotides.add(new Nucleotide(position, radius, fillColor));
    }
  }

  public void spin()
  {
    this.updateNucleotides();
    this.phase += this.deltaPhase;
  }

  private void updateNucleotides()
  {
    var start = this.vertices.x;
    var end = this.vertices.y;
    var p = 0;

    for (float x = start; x < end; x += this.pointSpacing)
    {
      var currentPhase = (this.phase + this.deltaPhase);
      var theta = currentPhase + TAU * x * this.frequency;
      var y = map(sin(theta), -1, 1, -this.amplitude, this.amplitude);

      var position = new PVector(x, y);
      var nucleotide = this.nucleotides.get(p);
      nucleotide.spin(position);

      p++;
    }
  }

  public void show()
  {
    if (this.nucleotides != null && !this.nucleotides.isEmpty())
    {
      for (var nucleotide : this.nucleotides)
        nucleotide.show();
    }
  }
}
