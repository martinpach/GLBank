using GLBankATM.Properties;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GLBankATM
{
    public enum States
    {
        LANGUAGE, ENTERPIN, PINOK, PINWRONG, INVALIDCARD
    }

    public enum Language
    {
        SVK, ENG
    }

    public partial class MainScreen : Form
    {
        private long id;       
        private Language language;
        private States state;
        //private Bitmap bitmap;
        //private Graphics g;     

        public MainScreen(long id)
        {
            InitializeComponent();
            changeState(States.LANGUAGE);                        
            this.id = id;
            /*bitmap = new Bitmap(371, 304);
            g = Graphics.FromImage(bitmap);
            g.Clear(Color.White);*/
        }

        private void changeState(States state)
        {
            this.state = state;
            foreach (Button button in this.Controls.OfType<Button>())
                button.Enabled = false;
            lblLeft1.Text = "";
            lblLeft2.Text = "";
            lblLeft3.Text = "";
            lblLeft4.Text = "";
            lblRight1.Text = "";
            lblRight2.Text = "";
            lblRight3.Text = "";
            lblRight4.Text = "";
            lblCenter.Text = "";

            switch (state)
            {
                case States.LANGUAGE:
                    btnLeft4.Enabled = true;
                    btnRight4.Enabled = true;
                    //writeLeft4("Slovak");
                    lblLeft4.Text = "English";
                    lblRight4.Text = "Slovak";
                    lblCenter.Text = "Choose language";
                    break;

                case States.ENTERPIN:
                    btn0.Enabled = true;
                    btn1.Enabled = true;
                    btn2.Enabled = true;
                    btn3.Enabled = true;
                    btn4.Enabled = true;
                    btn5.Enabled = true;
                    btn6.Enabled = true;
                    btn7.Enabled = true;
                    btn8.Enabled = true;
                    btn9.Enabled = true;
                    btnOk.Enabled = true;
                    btnCancel.Enabled = true;
                    if (language == Language.ENG)
                        lblCenter.Text = "Enter PIN";
                    else
                        lblCenter.Text = "Zadajte PIN";
                    break;               
            }
        }

        private void btnLeft4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.ENG;
                changeState(States.ENTERPIN);
            }
        }

        private void btnRight4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.SVK;
                changeState(States.ENTERPIN);
            }
        }

        /*private void writeLeft4(string text)
        {
            g.DrawString(text, new Font("Tahoma", 24), Brushes.Blue, new Point(50, 50));
            pictureBox1.Image = bitmap;
        }    */
    }
}
