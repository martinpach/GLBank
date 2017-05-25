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
        LANGUAGE, ENTERPIN, PINOK, PINWRONG, BLOCKEDCARD
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
        private string pinCode = "";
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
            lblCenterBottom.Text = "";

            switch (state)
            {
                case States.LANGUAGE:
                    btnLeft4.Enabled = true;
                    btnRight4.Enabled = true;
                    //writeLeft4("Slovak");
                    lblLeft4.Text = "English";
                    lblRight4.Text = "Slovak";
                    lblCenter.Text = "Choose language";
                    lblCenter.center();
                    break;

                case States.BLOCKEDCARD:
                    if (language == Language.ENG)
                        lblCenterBottom.Text = "CARD BLOCKED";                
                    else
                        lblCenterBottom.Text = "KARTA ZABLOKOVANA";
                    lblCenterBottom.center();
                    break;

                case States.ENTERPIN:
                    enableKeyboard();
                    if (language == Language.ENG)
                        lblCenter.Text = "Enter PIN";
                    else
                        lblCenter.Text = "Zadajte PIN";
                    lblCenter.center();
                    break;

                case States.PINOK:
                    lblCenter.Text = "PIN OK";
                    lblCenter.center();
                    break;

                case States.PINWRONG:
                    enableKeyboard();
                    lblCenter.Text = "PIN WRONG";
                    lblCenter.center();
                    break;  
            }
        }

        private void btnLeft4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.ENG;
                if (Database.isCardBlocked(id))
                    changeState(States.BLOCKEDCARD);
                else
                {
                    changeState(States.ENTERPIN);
                }                
            }
        }

        private void btnRight4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.SVK;
                if (Database.isCardBlocked(id))
                    changeState(States.BLOCKEDCARD);
                else
                {
                    changeState(States.ENTERPIN);
                }                
            }
        }

        private void enableKeyboard()
        {
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
        }

        private void btn1_Click(object sender, EventArgs e)
        {
            if((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '1';
            }
        }

        private void btn2_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '2';
            }
        }

        private void btn3_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '3';
            }
        }

        private void btn4_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '4';
            }
        }

        private void btn5_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '5';
            }
        }

        private void btn6_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '6';
            }
        }

        private void btn7_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '7';
            }
        }

        private void btn8_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '8';
            }
        }

        private void btn9_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '9';
            }
        }

        private void btn0_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                pinCode += '0';
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            if (States.ENTERPIN == state || States.PINWRONG == state)
            {
                lblCenterBottom.Text = "";
                pinCode = "";
            }
        }

        private void btnOk_Click(object sender, EventArgs e)
        {
            if ((States.ENTERPIN == state || States.PINWRONG == state) && pinCode.Length == 4)
            {              
                int pin = int.Parse(pinCode);
                if (Database.isPinCorrect(pin, id))
                    changeState(States.PINOK);
                else
                {
                    if (Database.getIncorrectPinAttempts(id) > 2)
                    {
                        Database.blockCard(id);
                        changeState(States.BLOCKEDCARD);
                    }
                    else
                    {
                        pinCode = "";
                        changeState(States.PINWRONG);
                    }
                }
            }
        }

        /*private void writeLeft4(string text)
        {
            g.DrawString(text, new Font("Tahoma", 24), Brushes.Blue, new Point(50, 50));
            pictureBox1.Image = bitmap;
        }    */
    }
}
