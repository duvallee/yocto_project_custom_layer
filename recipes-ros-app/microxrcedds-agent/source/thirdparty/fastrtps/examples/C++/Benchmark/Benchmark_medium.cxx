// Copyright 2016 Proyectos y Sistemas de Mantenimiento SL (eProsima).
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/*! 
 * @file Benchmark_medium.cpp
 * This source file contains the definition of the described types in the IDL file.
 *
 * This file was generated by the tool gen.
 */

#ifdef _WIN32
// Remove linker warning LNK4221 on Visual Studio
namespace { char dummy; }
#endif

#include "Benchmark_medium.h"

#include <fastcdr/Cdr.h>

#include <fastcdr/exceptions/BadParamException.h>
using namespace eprosima::fastcdr::exception;

#include <utility>

BenchMarkMedium::BenchMarkMedium()
{

    m_index = 0;
}

BenchMarkMedium::~BenchMarkMedium()
{
}

BenchMarkMedium::BenchMarkMedium(const BenchMarkMedium &x)
{
    m_data = x.m_data;
    m_index = x.m_index;
}

BenchMarkMedium::BenchMarkMedium(BenchMarkMedium &&x)
{
    m_data = std::move(x.m_data);
    m_index = x.m_index;
}

BenchMarkMedium& BenchMarkMedium::operator=(const BenchMarkMedium &x)
{
    m_data = x.m_data;
    m_index = x.m_index;
    
    return *this;
}

BenchMarkMedium& BenchMarkMedium::operator=(BenchMarkMedium &&x)
{
    m_data = std::move(x.m_data);
    m_index = x.m_index;
    
    return *this;
}

size_t BenchMarkMedium::getMaxCdrSerializedSize(size_t current_alignment)
{
    size_t initial_alignment = current_alignment;
            
    current_alignment += ((524288) * 1) + eprosima::fastcdr::Cdr::alignment(current_alignment, 1);

    current_alignment += 4 + eprosima::fastcdr::Cdr::alignment(current_alignment, 4);


    return current_alignment - initial_alignment;
}

size_t BenchMarkMedium::getCdrSerializedSize(const BenchMarkMedium&, size_t current_alignment)
{
    size_t initial_alignment = current_alignment;
            
    current_alignment += ((524288) * 1) + eprosima::fastcdr::Cdr::alignment(current_alignment, 1);
    current_alignment += 4 + eprosima::fastcdr::Cdr::alignment(current_alignment, 4);


    return current_alignment - initial_alignment;
}

void BenchMarkMedium::serialize(eprosima::fastcdr::Cdr &scdr) const
{
    scdr << m_data;
    scdr << m_index;
}

void BenchMarkMedium::deserialize(eprosima::fastcdr::Cdr &dcdr)
{
    dcdr >> m_data;
    dcdr >> m_index;
}

size_t BenchMarkMedium::getKeyMaxCdrSerializedSize(size_t current_alignment)
{
	size_t current_align = current_alignment;
            



    return current_align;
}

bool BenchMarkMedium::isKeyDefined()
{
    return false;
}

void BenchMarkMedium::serializeKey(eprosima::fastcdr::Cdr &) const
{
	 
	 
}