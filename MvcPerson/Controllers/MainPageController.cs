using Microsoft.AspNetCore.Mvc;
using System.Text.Encodings.Web;

namespace MvcPerson.Controllers
{
    public class MainPageController : Controller
    {
        // 
        // GET: /MainPage/

        public IActionResult Index()
        {
            return View();
        }

        // 
        // GET: /HelloWorld/Welcome/ 

        public IActionResult Welcome(string name, int numTimes = 1)
        {
            ViewData["Message"] = "Hello " + name;
            ViewData["NumTimes"] = numTimes;

            return View();
        }

    }
}