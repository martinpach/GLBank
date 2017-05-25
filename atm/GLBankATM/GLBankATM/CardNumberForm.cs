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
    public partial class CardNumberForm : Form
    {
        public CardNumberForm()
        {
            InitializeComponent();
        }

        private void btnSubmit_Click(object sender, EventArgs e)
        {
            String cardNumber = txtCardNumber.Text;
            long id;
            if(long.TryParse(cardNumber, out id) && Database.existCard(id))
            {
                this.Hide();
                MainScreen mainScreen = new MainScreen(id);
                mainScreen.ShowDialog();
                this.Show();
            }
            else
            {
                lblInvalidCard.Visible = true;
            }   
        }
    }
}
