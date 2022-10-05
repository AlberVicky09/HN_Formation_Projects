using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using MvcPerson.Models;

namespace MvcPerson.Controllers;

public class HomeController : Controller
{
    private readonly ILogger<HomeController> _logger;

    public HomeController(ILogger<HomeController> logger)
    {
        _logger = logger;
    }

    #region Pages
    public IActionResult Index(){ return View(); }
    public IActionResult AboutUs(){ return View(); }
    public IActionResult Contact(){ return View(); }
    public IActionResult Events(){ return View(); }
    public IActionResult Logos(){ return View(); }
    public IActionResult News(){ return View(); }
    public IActionResult PhotoGallery(){ return View(); }
    public IActionResult Photography(){ return View(); }
    public IActionResult Prints(){ return View(); }
    public IActionResult Websites(){ return View(); }
    #endregion Pages

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error()
    {
        return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
    }
}
