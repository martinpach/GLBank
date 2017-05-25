using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace GLBankATM
{
    public static class MyClass
    {
        public static void center(this Control component)
        {
            float compWidth = component.Width;
            float parentWidth = component.Parent.Width;
            float middled = (parentWidth / 2) - (compWidth / 2);

            component.Left = Convert.ToInt32(middled);
        }
    }
}
