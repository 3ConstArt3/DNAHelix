class Nucleotide
{

  private PVector position;
  private float radius;

  private color fillColor;

  /* Constructor definition */
  public Nucleotide(PVector position, float radius,
    color fillColor)
  {
    this.position = position;
    this.radius = radius;

    this.fillColor = fillColor;
  }

  /* Function definition */
  public void spin(PVector position)
  {
    this.position = position;
  }

  public void render()
  {
    pushMatrix();
    translate(this.position.x, this.position.y);

    noStroke();
    fill(this.fillColor);
    circle(0, 0, this.radius);
    popMatrix();
  }
}
