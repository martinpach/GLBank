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
        LANGUAGE, ENTERPIN, PINOK, PINWRONG, BLOCKEDCARD, BALANCE, CHANGEPIN, WITHDRAW, WITHDRAW_CUSTOM
    }

    public enum Language
    {
        SVK, ENG
    }

    public partial class MainScreen : Form
    {
        private long cardNumber;       
        private Language language;
        private States state;
        private string pinCode = "";
        private string amountToWithDraw = "";

        public MainScreen(long cardNumber)
        {
            InitializeComponent();
            this.CenterToScreen();
            changeState(States.LANGUAGE);                        
            this.cardNumber = cardNumber;
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
            lblRight4.Text = "Exit ->";
            lblCenter.Text = "";
            lblCenterBottom.Text = "";
            btnRight4.Enabled = true;
            if(state == States.BALANCE || state == States.CHANGEPIN || state == States.WITHDRAW || state == States.WITHDRAW_CUSTOM)
            {
                btnLeft4.Enabled = true;
                if (this.language == Language.ENG)
                    lblLeft4.Text = "Back";
                else
                    lblLeft4.Text = "Spat";
            }

            switch (state)
            {
                case States.LANGUAGE:
                    btnLeft4.Enabled = true;
                    btnRight4.Enabled = true;                    
                    lblLeft4.Text = "English";
                    lblRight4.Text = "Slovak";
                    lblCenter.Text = "Choose language";
                    lblCenter.center();
                    break;

                case States.BLOCKEDCARD:
                    lblCenterBottom.Text = Languages.getcardBlockedText(language);
                    lblCenterBottom.center();
                    break;

                case States.ENTERPIN:
                    enableKeyboard();
                    lblCenter.Text = Languages.getEnterPinText(language);
                    lblCenter.center();
                    break;

                case States.PINOK:
                    btnLeft2.Enabled = true;
                    btnLeft3.Enabled = true;
                    btnRight3.Enabled = true;
                    lblLeft2.Text = Languages.getmenuWithDrawText(language);
                    lblLeft3.Text = Languages.getmenuBalanceText(language);
                    lblRight3.Text = Languages.getmenuPinText(language);
                    break;

                case States.PINWRONG:
                    enableKeyboard();
                    lblCenter.Text = Languages.getwrongPinText(language);
                    lblCenter.center();
                    break;

                case States.BALANCE:
                    lblCenter.Text = Languages.getmenuBalanceText(language);
                    lblCenter.center();
                    lblCenterBottom.Text = "" + Database.getBalance(cardNumber) + "€";
                    lblCenterBottom.center();
                    break;

                case States.CHANGEPIN:
                    enableKeyboard();
                    lblCenter.Text = Languages.getchangePinText(language);                                         
                    lblCenter.center();                    
                    break;

                case States.WITHDRAW:
                    btnLeft1.Enabled = true;
                    btnLeft2.Enabled = true;
                    btnLeft3.Enabled = true;
                    btnRight2.Enabled = true;
                    lblLeft1.Text = "10";
                    lblLeft2.Text = "20";
                    lblLeft3.Text = "50";
                    lblRight2.Text = Languages.getotherAmountText(language);
                    break;

                case States.WITHDRAW_CUSTOM:
                    enableKeyboard();
                    lblCenter.Text = Languages.getenterValueText(language);
                    break;
            }
        }

        private void btnLeft4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.ENG;
                if (Database.isCardBlocked(cardNumber))
                    changeState(States.BLOCKEDCARD);
                else
                {
                    changeState(States.ENTERPIN);
                }                
            }
            else
                changeState(States.PINOK);
        }

        private void btnRight4_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.LANGUAGE))
            {
                language = Language.SVK;
                if (Database.isCardBlocked(cardNumber))
                    changeState(States.BLOCKEDCARD);
                else
                {
                    changeState(States.ENTERPIN);
                }                
            }
            else
            {
                this.Close();
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

        private void printSuccessWithdrawMessage()
        {
            lblCenterBottom.Text = Languages.gettakeMoneyText(language);
            lblCenterBottom.center();
        }

        private void printNotEnoughMoneyMessage()
        {
            if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenter.Text = Languages.getnotEnoughMoneyText(language);
                lblCenter.center();
            }
            else
            {
                lblCenterBottom.Text = Languages.getnotEnoughMoneyText(language);
                lblCenterBottom.center();
            }
        }

        private void printBadAmountMessage()
        {
            lblCenter.Text = Languages.getbadAmountText(language);
            lblCenter.center();
        }

        private void btn1_Click(object sender, EventArgs e)
        {
            if((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '1';
            }
            else if(this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "1";
                lblCenterBottom.center();
                this.amountToWithDraw += '1';
            }
        }

        private void btn2_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '2';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "2";
                lblCenterBottom.center();
                this.amountToWithDraw += '2';
            }
        }

        private void btn3_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '3';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "3";
                lblCenterBottom.center();
                this.amountToWithDraw += '3';
            }
        }

        private void btn4_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '4';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "4";
                lblCenterBottom.center();
                this.amountToWithDraw += '4';
            }
        }

        private void btn5_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '5';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "5";
                lblCenterBottom.center();
                this.amountToWithDraw += '5';
            }
        }

        private void btn6_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '6';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "6";
                lblCenterBottom.center();
                this.amountToWithDraw += '6';
            }
        }

        private void btn7_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '7';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "7";
                lblCenterBottom.center();
                this.amountToWithDraw += '7';
            }
        }

        private void btn8_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '8';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "8";
                lblCenterBottom.center();
                this.amountToWithDraw += '8';
            }
        }

        private void btn9_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '9';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "9";
                lblCenterBottom.center();
                this.amountToWithDraw += '9';
            }
        }

        private void btn0_Click(object sender, EventArgs e)
        {
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN)) && this.pinCode.Length < 4)
            {
                lblCenterBottom.Text += "*";
                lblCenterBottom.center();
                this.pinCode += '0';
            }
            else if (this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text += "0";
                lblCenterBottom.center();
                this.amountToWithDraw += '0';
            }
        }

        private void btnCancel_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG) || this.state.Equals(States.CHANGEPIN) || this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                lblCenterBottom.Text = "";
                this.pinCode = "";
                this.amountToWithDraw = "";
            }            
        }

        private void btnOk_Click(object sender, EventArgs e)
        {            
            if ((this.state.Equals(States.ENTERPIN) || this.state.Equals(States.PINWRONG)) && this.pinCode.Length == 4)
            {              
                int pin = int.Parse(this.pinCode);
                if (Database.isPinCorrect(pin, cardNumber))
                {
                    changeState(States.PINOK);
                    this.pinCode = "";
                }
                else
                {
                    if (Database.getIncorrectPinAttempts(cardNumber) > 2)
                    {
                        Database.blockCard(cardNumber);
                        changeState(States.BLOCKEDCARD);
                    }
                    else
                    {
                        this.pinCode = "";
                        changeState(States.PINWRONG);
                    }
                }
            }
            else if(this.state.Equals(States.CHANGEPIN) && this.pinCode.Length == 4)
            {
                int pin = int.Parse(this.pinCode);
                Database.updatePin(pin, cardNumber);
                lblCenter.Text = Languages.getpinChangedText(language);
                lblCenter.center();
                lblCenterBottom.Text = "";
            }
            else if(this.state.Equals(States.WITHDRAW_CUSTOM))
            {
                int value = int.Parse(this.amountToWithDraw);
                this.amountToWithDraw = "";                
                if(value % 5 == 0)
                {
                    if(Database.getBalance(cardNumber) >= value)
                    {
                        Database.subtractMoneyFromAccount(value, cardNumber);
                        printSuccessWithdrawMessage();
                    }
                    else
                    {
                        printNotEnoughMoneyMessage();
                    }
                }
                else
                {
                    printBadAmountMessage();
                }
            }
        }

        private void btnLeft3_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.PINOK))
            {
                changeState(States.BALANCE);
            }
            else if (this.state.Equals(States.WITHDRAW))
            {
                if (Database.getBalance(cardNumber) >= 50)
                {
                    Database.subtractMoneyFromAccount(50, cardNumber);
                    printSuccessWithdrawMessage();
                }
                else
                {
                    printNotEnoughMoneyMessage();
                }
            }
        }

        private void btnRight3_Click(object sender, EventArgs e)
        {
            if (this.state.Equals(States.PINOK))
            {
                changeState(States.CHANGEPIN);
            }
        }

        private void btnLeft2_Click(object sender, EventArgs e)
        {
            if(this.state.Equals(States.PINOK))
            {
                changeState(States.WITHDRAW);
            }
            else if(this.state.Equals(States.WITHDRAW))
            {
                if (Database.getBalance(cardNumber) >= 20)
                {
                    Database.subtractMoneyFromAccount(20, cardNumber);
                    printSuccessWithdrawMessage();                    
                }
                else
                {
                    printNotEnoughMoneyMessage();
                }
            }
        }

        private void btnLeft1_Click(object sender, EventArgs e)
        {
            if(this.state.Equals(States.WITHDRAW))
            {
                if(Database.getBalance(cardNumber) >= 10)
                {
                    Database.subtractMoneyFromAccount(10, cardNumber);
                    printSuccessWithdrawMessage();
                }
                else
                {
                    printNotEnoughMoneyMessage();
                }
            }
        }

        private void btnRight2_Click(object sender, EventArgs e)
        {
            if(this.state.Equals(States.WITHDRAW))
            {
                changeState(States.WITHDRAW_CUSTOM);
            }
        }
    }
}
