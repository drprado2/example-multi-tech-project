using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace dotnet.project.api.Controllers
{
    [Route("api/v1/hello-world")]
    [ApiController]
    public class HelloWorldController : ControllerBase
    {
        [HttpGet]
        public IActionResult Get([FromQuery] String nome="Sem Nome")
        {
            return Ok($"Ola {nome}");
        }
    }
}